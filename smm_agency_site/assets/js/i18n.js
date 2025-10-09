const defaultContent = JSON.parse(
  `{
    "site": {
      "brand": "",
      "nav": [],
      "socials": [],
      "footer_note_key": "footer_note"
    },
    "locale": "ru",
    "locales": {
      "ru": {},
      "az": {}
    },
    "services": [],
    "packages": [],
    "gallery": [],
    "faq": [],
    "about": {
      "hero": {
        "title": {},
        "subtitle": {},
        "imageSrc": ""
      },
      "stats": [],
      "values": [],
      "timeline": [],
      "team": []
    }
  }`
);

let content = JSON.parse(JSON.stringify(defaultContent));

const LOCALE_STORAGE_KEY = "smm_locale";

function readSavedLocale() {
  try {
    return window.sessionStorage.getItem(LOCALE_STORAGE_KEY);
  } catch (_) {
    return null;
  }
}

function persistLocale(loc) {
  try {
    window.sessionStorage.setItem(LOCALE_STORAGE_KEY, loc);
  } catch (_) {
    /* ignore */
  }
}

const savedLocale = typeof window !== "undefined" ? readSavedLocale() : null;
if (savedLocale) {
  content.locale = savedLocale;
}

function saveContent() {
  listeners.forEach((fn) => fn(content));
}

export function getContent() {
  return content;
}
export function setContent(newContent) {
  content = newContent;
  saveContent();
}
export function updateContent(mutator) {
  const c = getContent();
  mutator(c);
  saveContent();
}

export function getLocale() {
  return content.locale || "ru";
}
export function setLocale(loc) {
  content.locale = loc;
  persistLocale(loc);
  saveContent();
  refreshI18nText();
}

export function getT() {
  const loc = getLocale();
  const dict = content.locales?.[loc] || {};
  return (key) => dict[key] || key;
}

export function onContentChange(cb) {
  listeners.push(cb);
}
const listeners = [];

export function refreshI18nText() {
  const loc = getLocale();
  const dictRU = content.locales?.ru || {};
  const dictAZ = content.locales?.az || {};

  document.querySelectorAll("[data-i18n]").forEach((el) => {
    const key = el.getAttribute("data-i18n");
    const wantBoth = el.hasAttribute("data-i18n-both");

    if (wantBoth) {
      const ru = dictRU[key] || key;
      const az = dictAZ[key] || key;
      // Отрисуем оба языка аккуратно
      el.innerHTML = `
        <span>${ru}</span>
        <span style="opacity:.65; font-weight:500; margin-left:.5em;">/ ${az}</span>
      `;
    } else {
      // Старое поведение: один активный язык
      const dict = loc === "az" ? dictAZ : dictRU;
      el.textContent = dict[key] || key;
    }
  });
}
// Expose for Dashboard
export function exportJSON() {
  return JSON.stringify(content, null, 2);
}
export function importJSON(jsonStr) {
  const parsed = JSON.parse(jsonStr);
  setContent(parsed);
  refreshI18nText();
}

document.addEventListener("DOMContentLoaded", () => {
  refreshI18nText();
});
