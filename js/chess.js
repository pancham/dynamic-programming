/**
 * In the game of chess a Knight can move 2.5 steps (a square that can be reached by moving two squares horizontally
 *  and one square vertically, or two squares vertically and one square horizontally). 
 * Picture 9.9 shows all possible moves of a knight. A King, can move only one step 
 * (either horizontally, vertically or diagonally). Valid moves of a king is shown in Picture 9.10. We have designed a special 
 * piece that can move either like a knight or like a king. If that piece is named P, then all valid moves of P are shown in 
 * Picture 9.11. It is union of moves of Knight and King. Given that P is in a particular cell, and you want to move it to 
 * another cell then what is the minimum number of moves it takes P to go from source to destination. Write a function that 
 * accepts source and destination cells and return the minimum number of moves it will take P to move from source to destination
 * cell.
*/

// 6 x 6 array
let row = 6, col = 6
let chess = Array.from(Array(6), () => new Array(6))
// let visited = Array.from(Array(6), () => new Array(6))
let from = Array.from(Array(6), () => new Array(6))

start = [2,2]
end = [5, 3]

chess[start[0]][start[1]] = true
let stack = []
stack.push({i: 2, j: 2})
from[2][2] = {i: 2, j: 2}

let reached = false;
while (!reached && stack.length > 0) {
    let s = stack.pop();
    if (!from[s.i][s.j].visited) {
        // move one step in all directions (sentry)
        moveTo(s.i, s.j, s.i, s.j - 1)
        moveTo(s.i, s.j, s.i - 1, s.j - 1)
        moveTo(s.i, s.j, s.i - 1, s.j)
        moveTo(s.i, s.j, s.i - 1, s.j + 1)
        moveTo(s.i, s.j, s.i, s.j + 1)
        moveTo(s.i, s.j, s.i + 1, s.j + 1)
        moveTo(s.i, s.j, s.i + 1, s.j)
        moveTo(s.i, s.j, s.i + 1, s.j - 1)

        // horse move
        moveTo(s.i, s.j, s.i - 2, s.j - 1)
        moveTo(s.i, s.j, s.i + 2, s.j - 1)
        moveTo(s.i, s.j, s.i - 1, s.j - 2)
        moveTo(s.i, s.j, s.i + 1, s.j - 2)

        moveTo(s.i, s.j, s.i - 2, s.j + 1)
        moveTo(s.i, s.j, s.i + 2, s.j + 1)
        moveTo(s.i, s.j, s.i - 1, s.j + 2)
        moveTo(s.i, s.j, s.i + 1, s.j + 2)
    }

    from[s.i][s.j].visited = true


}

function moveTo(si, sj, di, dj) {
    if (di >= 0 && di < row && dj >= 0 && dj < col) {
        chess[di][dj] = true
        from[di][dj] = {i: si, j: sj}
    
        stack.push({i: di, j: dj})

        if (di == end[0] && dj == end[1]) {
            reached = true
        }
    }
}

console.log('chess', chess)
console.log('from', from)
console.log('stack', stack)
console.log('****************')
console.log('reached', reached)
console.log('finished')