import { draw, drawCO, scale } from "./canvas.js";
export const canvas = document.getElementById('canvas');
export const ctx = canvas.getContext("2d");
export const points = [];
export function R() {
    return document.querySelector('[id$="rInput"]');
}

window.setR = function (val) {
    const rEl = R();
    if (!rEl) return;
    rEl.value = val;
    draw();
};

export function Y() {
    return document.querySelector('[id$="Y"]');
}

export const X = document.querySelector('select[id$="X"]');

if (X) {
    X.addEventListener("change", () => {
        draw();
    });
}

draw();

document.querySelector('[id$="query1"]').addEventListener("click", draw);
document.querySelector('[id$="query2"]').addEventListener("click", drawCO);

canvas.addEventListener('click', (e) => {
    const rect = canvas.getBoundingClientRect();
    let xNum = ((e.clientX - rect.left - 190) / scale);
    let yNum = (-(e.clientY - rect.top - 190) / scale);
    xNum = Math.max(-5, Math.min(3, xNum));
    yNum = Math.max(-5, Math.min(5, yNum));
    const xInt = Math.round(xNum);
    if (X) {
        X.value = xInt.toString();
    }
    const yEl = Y();
    if (yEl) {
        yEl.value = yNum.toFixed(2);

    }
    draw();
    document.querySelector('[id$="query1"]').click();
});

export function updatePoints() {
    const tbody = document.querySelector('#table tbody');
    points.length = 0;
    const rows = tbody.querySelectorAll('tr');
    rows.forEach(row => {
        const cells = row.querySelectorAll('td');
        const Rv = parseFloat(cells[0].textContent.trim());
        const Xv = parseFloat(cells[1].textContent.trim());
        const Yv = parseFloat(cells[2].textContent.trim());
        const resultText = cells[3].textContent.trim();
        const result = resultText === "Попадание";
        points.push({ R: Rv, X: Xv, Y: Yv, result });
    });
}
