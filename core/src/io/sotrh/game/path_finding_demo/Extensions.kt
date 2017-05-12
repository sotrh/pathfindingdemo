package io.sotrh.game.path_finding_demo

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.utils.Disposable

/**
 * Created by benjamin on 5/11/17
 */

inline fun <reified T : Disposable> T.using(block: (T) -> Unit): T {
    block(this)
    dispose()
    return this
}

inline fun <reified T : Batch> T.batched(block: (T) -> Unit): T {
    begin()
    block(this)
    end()
    return this
}