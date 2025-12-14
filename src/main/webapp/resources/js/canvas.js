import {ctx, canvas, X, Y, R, points, updatePoints} from "./script.js";

export function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawTriangleQ2();
    drawCircleQ3();
    drawRectQ4();
    makeXOY();
    drawPoints();
}

export function drawCO() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawTriangleQ2();
    drawCircleQ3();
    drawRectQ4();
    makeXOY();
    const radius = 3;
    let x = Number(X.value) * scale + 190;
    let y = -Number(parseFloat(Y().value)) * scale + 190;
    ctx.beginPath();
    ctx.arc(x, y, radius, 0, Math.PI * 2);
    ctx.fillStyle = "#4c835a";
    ctx.fill();
    ctx.strokeStyle = "#4c835a";
    ctx.stroke();
}

window.drawCO = drawCO;
window.draw = draw;
const division = 5;
export const scale = 190 / (division + 1);
const centerX = 190;
const centerY = 190;

function isHit(x, y, r) {
    const halfR = r / 2.0;
    if (x <= 0 && y >= 0) { // 2
        if (x >= -r) {
            const edge = 0.5 * (x + r);
            if (y >= 0 && y <= edge && y <= halfR) {
                return true;
            }
        }
    }
    if (x <= 0 && y <= 0) { // 3
        if (x * x + y * y <= halfR * halfR) {
            return true;
        }
    }
    if (x >= 0 && y <= 0) { // 4
        if (x <= r && y >= -r) {
            return true;
        }
    }
    return false;
}

function drawPoints() {
    const radius = 3;
    const currentR = parseFloat(R().value.replace(",", "."));
    const curX = parseInt(X.value, 10);
    const curY = parseFloat(Y().value.replace(",", "."));

    if (!isNaN(curX) && !isNaN(curY)) {
        const curScreenX = curX * scale + centerX;
        const curScreenY = -curY * scale + centerY;

        ctx.beginPath();
        ctx.arc(curScreenX, curScreenY, radius, 0, Math.PI * 2);

        if (isHit(curX, curY, currentR)) {
            ctx.fillStyle = "#4c835a";
            ctx.fill();
        } else {
            ctx.strokeStyle = "#4c835a";
            ctx.lineWidth = 2;
            ctx.stroke();
        }
    }

    updatePoints();
    points.forEach(point => {
        const screenX = point.X * scale + centerX;
        const screenY = -point.Y * scale + centerY;
        ctx.beginPath();
        ctx.arc(screenX, screenY, radius, 0, Math.PI * 2);
        if (isHit(point.X, point.Y, currentR)) {
            ctx.fillStyle = "#4c835a";
            ctx.fill();
        } else {
            ctx.strokeStyle = "#4c835a";
            ctx.lineWidth = 2;
            ctx.stroke();
        }
    });
}

function drawTriangleQ2() {
    let r = parseFloat(R().value.replace(",", "."));
    ctx.beginPath();
    ctx.strokeStyle = "#b3dbbd";
    ctx.fillStyle = "#b3dbbde5";
    ctx.lineWidth = 0.8;
    ctx.moveTo(centerX, centerY);                           // (0,0)
    ctx.lineTo(centerX - r * scale, centerY);           // (-R,0)
    ctx.lineTo(centerX, centerY - (r * scale) / 2.0);   // (0,R/2)
    ctx.closePath();
    ctx.fill();
    ctx.stroke();
}

function drawCircleQ3() {
    let r = parseFloat(R().value.replace(",", "."));
    const rad = r * scale / 2.0;
    ctx.beginPath();
    ctx.strokeStyle = "#b3dbbd";
    ctx.fillStyle = "#b3dbbde5";
    ctx.lineWidth = 0.8;
    ctx.moveTo(centerX, centerY);
    ctx.arc(centerX, centerY, rad, Math.PI / 2, Math.PI, false);
    ctx.lineTo(centerX, centerY);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();
}

function drawRectQ4() {
    let r = parseFloat(R().value.replace(",", "."));
    ctx.beginPath();
    ctx.strokeStyle = "#b3dbbd";
    ctx.fillStyle = "#b3dbbde5";
    ctx.lineWidth = 0.8;
    ctx.fillRect(centerX, centerY, r * scale, r * scale);
    ctx.closePath();
    ctx.stroke();
}

function makeXOY() {
    ctx.beginPath();
    ctx.strokeStyle = "#000000ff";
    ctx.fillStyle = "#000000ff";
    ctx.lineWidth = 0.8;
    ctx.moveTo(centerX, centerY);
    ctx.lineTo(centerX, 380);
    ctx.moveTo(centerX, centerY);
    ctx.lineTo(centerX, 0);
    ctx.lineTo(198, 7);
    ctx.moveTo(centerX, 0);
    ctx.lineTo(182, 7);
    ctx.fillText("Y", 210, 9);
    ctx.moveTo(centerX, centerY);
    ctx.lineTo(0, centerY);
    ctx.moveTo(centerX, centerY);
    ctx.lineTo(380, centerY);
    ctx.lineTo(373, 198);
    ctx.moveTo(380, centerY);
    ctx.lineTo(373, 182);
    ctx.fillText("X", 371, 170);
    ctx.closePath();
    ctx.stroke();
    for (let i = scale; i < 180; i += scale) {
        makeOX(i / scale, centerX + i, centerY);
        makeOX(-i / scale, centerX - i, centerY);
    }
    for (let i = scale; i < 180; i += scale) {
        makeOY(-i / scale, centerX, centerY + i);
        makeOY(i / scale, centerX, centerY - i);
    }
}

function makeOX(i, x, y) {
    ctx.beginPath();
    ctx.strokeStyle = "#000000ff";
    ctx.fillStyle = "#000000ff";
    ctx.lineWidth = 0.8;
    ctx.moveTo(x, y);
    ctx.lineTo(x, y + 5);
    ctx.moveTo(x, y);
    ctx.lineTo(x, y - 5);
    ctx.moveTo(x, y);
    ctx.fillText(i, x - 3, y + 17);
    ctx.closePath();
    ctx.stroke();
}

function makeOY(i, x, y) {
    ctx.beginPath();
    ctx.strokeStyle = "#000000ff";
    ctx.fillStyle = "#000000ff";
    ctx.lineWidth = 0.8;
    ctx.moveTo(x, y);
    ctx.lineTo(x + 5, y);
    ctx.moveTo(x, y);
    ctx.lineTo(x - 5, y);
    ctx.moveTo(x, y);
    ctx.fillText(i, x + 12, y + 4);
    ctx.closePath();
    ctx.stroke();
}
