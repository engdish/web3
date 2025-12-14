function updateClock() {
    var el = document.getElementById("clock");
    if (!el) return;
    el.textContent = new Date().toLocaleTimeString('ru-RU');
}
updateClock();
setInterval(updateClock, 6000);