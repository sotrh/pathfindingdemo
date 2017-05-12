package io.sotrh.game.path_finding_demo.grid

import com.badlogic.gdx.graphics.Pixmap

/**
 * Created by benjamin on 5/11/17
 */

typealias Grid = List<List<Int>>

val CELL_TYPE_WALL = 0x00_00_00
val CELL_TYPE_GROUND = 0xff_ff_ff
val CELL_TYPE_GOAL = 0xff_80_00
val CELL_TYPE_START = 0x00_00_ff

fun gridFromPixmap(pixmap: Pixmap): Grid {
    return (0..pixmap.width - 1).map { x ->
        (0..pixmap.height - 1).map { y ->
            pixmap.getPixel(x, y)
        }
    }
}