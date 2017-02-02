package com.cobble.bitlogic

import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.badlogic.gdx.graphics.{Color, GL20, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Logger

class BitLogic extends ApplicationAdapter {

    lazy val batch: SpriteBatch = new SpriteBatch()
    lazy val img: Texture = new Texture(Gdx.files.internal("badlogic.jpg"))

    val rv: Float = 0.01f
    var ratio: Float = 0.0f
    var close: Color = new Color(Math.random.toFloat, Math.random.toFloat, Math.random.toFloat, 1.0f)
    var far: Color = new Color(Math.random.toFloat, Math.random.toFloat, Math.random.toFloat, 1.0f)
    var currentColor: Color = new Color()
    //    val near

    override def create(): Unit = {

    }

    def tick(): Unit = {
        if (ratio >= 1.0f) {
            ratio = 0.0f
            close.set(far)
            far.set(Math.random().toFloat, Math.random().toFloat, Math.random().toFloat, 1.0f)
        }
        ratio += rv

        currentColor = close.cpy().lerp(far, ratio)
//        Gdx.app.log("BitLogic|Ratio", s"$ratio | $rv")
//        Gdx.app.log("BitLogic|Color", s"$currentColor")
//        Gdx.app.log("BitLogic|Points", s"$close | $far")
    }

    override def render(): Unit = {
        tick()
        Gdx.gl.glClearColor(currentColor.r, currentColor.g, currentColor.b, currentColor.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        batch.draw(img, 0, 0)
        batch.end()
    }

    override def dispose(): Unit = {
        batch.dispose()
        img.dispose()
    }
}