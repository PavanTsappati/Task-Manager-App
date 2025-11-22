// script.js - API helpers and common utils used by pages
const API_BASE = ''; // calling relative /api paths; backend on same origin

function getAuth() {
  return sessionStorage.getItem('basicAuth');
}

function apiFetch(path, opts = {}) {
  const headers = opts.headers || {};
  const token = getAuth();
  if (token) {
    headers['Authorization'] = 'Basic ' + token;
  }
  headers['Content-Type'] = headers['Content-Type'] || 'application/json';
  opts.headers = headers;
  // ensure no caching for API calls
  opts.cache = 'no-store';

  return fetch(API_BASE + path, opts).then(async res => {
    if (res.status === 401) {
      // unauthorized -> force logout
      sessionStorage.removeItem('basicAuth');
      alert('Session expired. Please login again.');
      location.href = 'login.html';
      throw new Error('Unauthorized');
    }
    const text = await res.text();
    try {
      return JSON.parse(text);
    } catch (e) {
      return text;
    }
  });
}

function apiGet(path) {
  return apiFetch(path, { method: 'GET' });
}
function apiPost(path, data) {
  return apiFetch(path, { method: 'POST', body: JSON.stringify(data) });
}
function apiPut(path, data) {
  return apiFetch(path, { method: 'PUT', body: JSON.stringify(data) });
}
function apiDelete(path) {
  return apiFetch(path, { method: 'DELETE' });
}

// small helpers
function escapeHtml(s) {
  if (s === undefined || s === null) return '';
  return String(s)
    .replaceAll('&','&amp;')
    .replaceAll('<','&lt;')
    .replaceAll('>','&gt;')
    .replaceAll('"','&quot;')
    .replaceAll("'",'&#39;');
}

function formatDateOnly(iso) {
  if (!iso) return '';
  const d = new Date(iso);
  if (isNaN(d)) {
    // fallback if Instant without timezone
    return (iso.split && iso.split('T') && iso.split('T')[0]) || iso;
  }
  const yyyy = d.getFullYear();
  const mm = String(d.getMonth() + 1).padStart(2,'0');
  const dd = String(d.getDate()).padStart(2,'0');
  return `${yyyy}-${mm}-${dd}`;
}
