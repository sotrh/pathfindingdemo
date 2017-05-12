package io.sotrh.game.path_finding_demo

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.sotrh.game.path_finding_demo.grid.Grid
import io.sotrh.game.path_finding_demo.grid.gridFromPixmap

class PathFindingDemo : ApplicationAdapter() {
    private lateinit var texture: Texture
    private lateinit var batch: SpriteBatch
    private lateinit var grid: Grid
    private var offsetX = 0f
    private var offsetY = 0f

    companion object {
        private var SCALE = 50f
    }

    override fun create() {
        Pixmap(1, 1, Pixmap.Format.RGB888).using {
            it.setColor(Color.WHITE)
            it.fill()
            texture = Texture(it)
        }

        Pixmap(Gdx.files.internal("maze-1.png")).using {
            offsetX = (Gdx.graphics.width - it.width * SCALE) * 0.5f
            offsetY = (Gdx.graphics.height - it.height * SCALE) * 0.5f
            grid = gridFromPixmap(it)
        }

        batch = SpriteBatch()
    }

    override fun render() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
            return
        }

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.batched { batch ->
            grid.forEachIndexed { x, list ->
                list.forEachIndexed { y, cell ->
                    batch.color = Color(cell)
                    batch.draw(
                            texture,
                            x * SCALE + offsetX,
                            y * SCALE + offsetY,
                            SCALE,
                            SCALE
                    )
                }
            }
        }
    }

    override fun dispose() {
        texture.dispose()
        batch.dispose()
    }
}
