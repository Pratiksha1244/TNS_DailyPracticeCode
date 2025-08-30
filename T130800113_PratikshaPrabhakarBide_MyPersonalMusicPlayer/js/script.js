/* MyPersonal Music Player - script.js
   Implements: play/pause, next/prev, seek, volume, playlist, favorites,
   recently played, search (text + voice), shuffle, repeat, visitor count.
*/

// Elements
const audio = new Audio();
const playlistEl = document.getElementById('playlist');
const favoritesEl = document.getElementById('favoritesList');
const recentEl = document.getElementById('recentList');
const titleEl = document.getElementById('title');
const artistEl = document.getElementById('artist');
const coverEl = document.getElementById('cover');
const playPauseBtn = document.getElementById('playPauseBtn');
const prevBtn = document.getElementById('prevBtn');
const nextBtn = document.getElementById('nextBtn');
const seekBar = document.getElementById('seekBar');
const currentTimeEl = document.getElementById('currentTime');
const durationEl = document.getElementById('duration');
const volumeEl = document.getElementById('volume');
const shuffleBtn = document.getElementById('shuffleBtn');
const repeatBtn = document.getElementById('repeatBtn');
const favViewBtn = document.getElementById('favViewBtn');
const recentViewBtn = document.getElementById('recentViewBtn');
const searchInput = document.getElementById('searchInput');
const voiceSearchBtn = document.getElementById('voiceSearchBtn');
const visitorCountEl = document.getElementById('visitorCount');

let songs = [];             // loaded from songs.json
let currentIndex = 0;
let isPlaying = false;
let isShuffle = false;
let repeatMode = 'off';     // off | one | all

// data keys in localStorage
const LS_FAVORITES = 'mpplayer_favorites_v1';
const LS_RECENT = 'mpplayer_recent_v1';
const LS_VISIT = 'mpplayer_visits_v1';

function loadJSON() {
  return fetch('js/songs.json')
    .then(r => r.json())
    .then(data => {
      songs = data.songs;
      buildPlaylist(songs);
      loadFavoritesUI();
      loadRecentUI();
      loadSong(0, false);
    })
    .catch(err => console.error('Failed to load songs.json', err));
}

function buildPlaylist(list) {
  playlistEl.innerHTML = '';
  list.forEach((s, idx) => {
    const li = document.createElement('li');
    li.dataset.index = idx;
    li.innerHTML = `
      <img class="item-cover" src="${s.albumImage}" alt="cover" />
      <div class="item-meta">
        <div class="item-title">${s.title}</div>
        <div class="item-sub">${s.artist}</div>
      </div>
      <div class="item-actions">
        <button class="fav-btn" data-index="${idx}" title="Toggle favorite">❤</button>
      </div>
    `;
    li.addEventListener('click', (e) => {
      if (e.target && e.target.classList.contains('fav-btn')) return; // handled separately
      playIndex(parseInt(li.dataset.index));
    });
    const favBtn = li.querySelector('.fav-btn');
    favBtn.addEventListener('click', (e) => {
      e.stopPropagation();
      toggleFavorite(idx);
      updateFavBtnUI(favBtn, idx);
    });
    updateFavBtnUI(li.querySelector('.fav-btn'), idx);
    playlistEl.appendChild(li);
  });
}

function updateFavBtnUI(btn, idx) {
  const favs = getFavorites();
  if (!btn) return;
  btn.textContent = favs.includes(songs[idx].id) ? '💖' : '🤍';
}

function loadSong(index, autoPlay=true) {
  if (!songs || !songs[index]) return;
  currentIndex = index;
  const s = songs[index];
  audio.src = s.filePath;
  titleEl.textContent = s.title;
  artistEl.textContent = s.artist;
  coverEl.src = s.albumImage;
  // update playlist selected style
  Array.from(playlistEl.children).forEach(li => li.classList.remove('active'));
  const activeLi = playlistEl.querySelector(`li[data-index="${index}"]`);
  if (activeLi) activeLi.classList.add('active');

  // Show duration when loaded
  audio.addEventListener('loadedmetadata', () => {
    durationEl.textContent = formatTime(audio.duration);
    seekBar.max = Math.floor(audio.duration);
  }, { once: true });

  // Add to recently played
  addToRecent(s.id);

  if (autoPlay) play();
}

function playIndex(index) {
  loadSong(index, true);
}

function play() {
  audio.play().then(() => {
    isPlaying = true;
    playPauseBtn.textContent = '⏸';
    coverEl.classList.add('rotate');
  }).catch(e => {
    // Autoplay blocked or error
    isPlaying = false;
    playPauseBtn.textContent = '▶️';
    console.warn('Play prevented', e);
  });
}

function pause() {
  audio.pause();
  isPlaying = false;
  playPauseBtn.textContent = '▶️';
  coverEl.classList.remove('rotate');
}

playPauseBtn.addEventListener('click', () => {
  if (!audio.src) return;
  if (isPlaying) pause(); else play();
});

prevBtn.addEventListener('click', () => {
  if (audio.currentTime > 3) {
    audio.currentTime = 0;
  } else {
    if (isShuffle) {
      nextRandom();
    } else {
      currentIndex = (currentIndex -1 + songs.length) % songs.length;
      loadSong(currentIndex, true);
    }
  }
});

nextBtn.addEventListener('click', () => {
  if (isShuffle) nextRandom();
  else {
    if (currentIndex === songs.length -1) {
      if (repeatMode === 'all') {
        currentIndex = 0;
        loadSong(currentIndex, true);
      } else {
        // end
        pause();
        audio.currentTime = 0;
      }
    } else {
      currentIndex++;
      loadSong(currentIndex, true);
    }
  }
});

function nextRandom(){
  let next = Math.floor(Math.random() * songs.length);
  // avoid immediate repeat if possible
  if (songs.length > 1 && next === currentIndex) {
    next = (next + 1) % songs.length;
  }
  loadSong(next, true);
}

// update seek bar and current time while playing
audio.addEventListener('timeupdate', () => {
  if (!isNaN(audio.duration)) {
    seekBar.value = Math.floor(audio.currentTime);
    currentTimeEl.textContent = formatTime(audio.currentTime);
  }
});

// when song ends
audio.addEventListener('ended', () => {
  if (repeatMode === 'one') {
    audio.currentTime = 0;
    play();
  } else if (isShuffle) {
    nextRandom();
  } else {
    if (currentIndex === songs.length - 1) {
      if (repeatMode === 'all') loadSong(0, true);
      else { pause(); audio.currentTime = 0; }
    } else {
      currentIndex++;
      loadSong(currentIndex, true);
    }
  }
});

// seek bar control
seekBar.addEventListener('input', () => {
  audio.currentTime = seekBar.value;
  currentTimeEl.textContent = formatTime(audio.currentTime);
});

// volume
volumeEl.addEventListener('input', () => {
  audio.volume = volumeEl.value;
});

// format seconds -> mm:ss
function formatTime(sec){
  if (!sec || isNaN(sec)) return '0:00';
  const s = Math.floor(sec % 60).toString().padStart(2,'0');
  const m = Math.floor(sec / 60);
  return `${m}:${s}`;
}

/* ========= favorites (localStorage) ========== */
function getFavorites(){
  try {
    const raw = localStorage.getItem(LS_FAVORITES);
    return raw ? JSON.parse(raw) : [];
  } catch(e){ return []; }
}
function setFavorites(list){
  localStorage.setItem(LS_FAVORITES, JSON.stringify(list));
}
function toggleFavorite(idx){
  const id = songs[idx].id;
  const favs = getFavorites();
  const pos = favs.indexOf(id);
  if (pos === -1) favs.unshift(id);
  else favs.splice(pos,1);
  setFavorites(favs);
  loadFavoritesUI();
  buildPlaylist(songs); // to refresh heart UI
}
function loadFavoritesUI(){
  const favs = getFavorites();
  favoritesEl.innerHTML = '';
  favs.forEach(id => {
    const s = songs.find(x => x.id === id);
    if (!s) return;
    const li = document.createElement('li');
    li.innerHTML = `<img class="item-cover" src="${s.albumImage}" alt="" />
                    <div class="item-meta"><div>${s.title}</div><div class="item-sub">${s.artist}</div></div>`;
    li.addEventListener('click', () => {
      const idx = songs.indexOf(s);
      if (idx >= 0) playIndex(idx);
    });
    favoritesEl.appendChild(li);
  });
}

/* ========= recently played (localStorage) ========== */
function getRecent(){
  try {
    const raw = localStorage.getItem(LS_RECENT);
    return raw ? JSON.parse(raw) : [];
  } catch(e){ return []; }
}
function setRecent(list){
  localStorage.setItem(LS_RECENT, JSON.stringify(list));
}
function addToRecent(id){
  const recent = getRecent();
  // remove if exists and place at start
  const idx = recent.indexOf(id);
  if (idx !== -1) recent.splice(idx,1);
  recent.unshift(id);
  if (recent.length > 20) recent.length = 20;
  setRecent(recent);
  loadRecentUI();
}
function loadRecentUI(){
  const recent = getRecent();
  recentEl.innerHTML = '';
  recent.forEach(id => {
    const s = songs.find(x => x.id === id);
    if (!s) return;
    const li = document.createElement('li');
    li.innerHTML = `<img class="item-cover" src="${s.albumImage}" alt="" />
                    <div class="item-meta"><div>${s.title}</div><div class="item-sub">${s.artist}</div></div>`;
    li.addEventListener('click', ()=> {
      const idx = songs.indexOf(s);
      if (idx >= 0) playIndex(idx);
    });
    recentEl.appendChild(li);
  });
}

/* ========= search (text + voice) ========= */
function filterPlaylist(query){
  const q = query.trim().toLowerCase();
  const filtered = songs.filter(s => (s.title + ' ' + s.artist).toLowerCase().includes(q));
  buildPlaylist(filtered);
}

// text search
searchInput.addEventListener('input', (e) => {
  const q = e.target.value;
  if (!q) buildPlaylist(songs);
  else filterPlaylist(q);
});

// voice search - Web Speech API
let recognition;
if ('webkitSpeechRecognition' in window || 'SpeechRecognition' in window){
  const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
  recognition = new SpeechRecognition();
  recognition.lang = 'en-US';
  recognition.interimResults = false;
  recognition.maxAlternatives = 1;

  voiceSearchBtn.addEventListener('click', () => {
    try {
      recognition.start();
      voiceSearchBtn.textContent = '⏺';
    } catch(err){ console.warn(err); }
  });

  recognition.addEventListener('result', (e) => {
    const text = e.results[0][0].transcript;
    searchInput.value = text;
    filterPlaylist(text);
    voiceSearchBtn.textContent = '🎤';
  });

  recognition.addEventListener('end', () => { voiceSearchBtn.textContent = '🎤'; });
} else {
  voiceSearchBtn.addEventListener('click', () => {
    alert('Voice search is not supported in this browser.');
  });
}

/* ========= shuffle & repeat ========= */
shuffleBtn.addEventListener('click', () => {
  isShuffle = !isShuffle;
  shuffleBtn.style.opacity = isShuffle ? '1' : '0.6';
});
repeatBtn.addEventListener('click', () => {
  if (repeatMode === 'off') repeatMode = 'all';
  else if (repeatMode === 'all') repeatMode = 'one';
  else repeatMode = 'off';
  repeatBtn.textContent = repeatMode === 'one' ? '🔂' : '🔁';
  repeatBtn.style.opacity = repeatMode === 'off' ? '0.6' : '1';
});

/* ========= visitor counter ========= */
(function visitorCount(){
  try {
    let v = parseInt(localStorage.getItem(LS_VISIT) || '0', 10);
    v = v + 1;
    localStorage.setItem(LS_VISIT, v.toString());
    visitorCountEl.textContent = v;
  } catch(e){ visitorCountEl.textContent = '0'; }
})();

/* ========= favorites/recent view toggles ========= */
favViewBtn.addEventListener('click', () => {
  favoritesEl.parentElement.scrollIntoView({behavior:'smooth'});
});
recentViewBtn.addEventListener('click', () => {
  recentEl.parentElement.scrollIntoView({behavior:'smooth'});
});

/* ========= helpers ========= */
function playIfNotPaused(){
  if (!isPlaying) play();
}

// on page load
window.addEventListener('DOMContentLoaded', () => {
  loadJSON();
  // try to keep volume setting from previous session
  try {
    const vol = parseFloat(localStorage.getItem('mpplayer_volume_v1') || '1');
    volumeEl.value = vol;
    audio.volume = vol;
  } catch(e){}
});

// store volume change persistently
volumeEl.addEventListener('change', () => {
  localStorage.setItem('mpplayer_volume_v1', volumeEl.value);
});
