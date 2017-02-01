package com.cobble.bitlogic

import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Logger

class BitLogic extends ApplicationAdapter {

    case class Color(var r: Float, var g: Float, var b: Float) {
        override def toString: String = s"($r, $g, $b)"
    }

    lazy val batch: SpriteBatch = new SpriteBatch()
    lazy val img: Texture = new Texture("badlogic.jpg")

    var ratio: Float = 0.0f
    var close: Color = Color(Math.random.toFloat, Math.random.toFloat, Math.random.toFloat)
    var far: Color = Color(Math.random.toFloat, Math.random.toFloat, Math.random.toFloat)
    var currentColor: Color = close
    //    val near

    override def create(): Unit = {

    }

    def tick(): Unit = {

        currentColor.r += getDelta
        currentColor.g += getDelta
        currentColor.b += getDelta
        currentColor.r = Math.max(Math.min(currentColor.r, 1.0), 0.0).toFloat
        currentColor.g = Math.max(Math.min(currentColor.g, 1.0), 0.0).toFloat
        currentColor.b = Math.max(Math.min(currentColor.b, 1.0), 0.0).toFloat
        Gdx.app.log("BitLogic", currentColor.toString())
    }

    override def render(): Unit = {
        tick()
        Gdx.gl.glClearColor(currentColor.r, currentColor.g, currentColor.b, 1)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        batch.draw(img, 0, 0)
        batch.end()
    }

    override def dispose(): Unit = {
        batch.dispose()
        img.dispose()
    }

    def getDelta: Float = {
        val offset: Float = 0.5f
        val scale: Float = 0.0125f
        (Math.random.toFloat * scale) - (offset * scale)
    }

}
