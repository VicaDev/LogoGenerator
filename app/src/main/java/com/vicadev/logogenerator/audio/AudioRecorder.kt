package com.vicadev.logogenerator.audio

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import java.io.File
import java.io.FileOutputStream

class AudioRecorder(private val context: Context) {
    var recorder: MediaRecorder? = null

    private fun create(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else MediaRecorder()
    }

    fun record(file: File) {
        create().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC) //Indicamos que grabe con el micrófono.
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4) //Formato para la grabación.
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC) //El encoder que va a tratar el audio.
            setOutputFile(FileOutputStream(file).fd) //Fichero por el cúal se guardará el audio.

            prepare() //Preparado para grabar.
            start() //Inicia la grabación.

            recorder = this
        }
    }

    fun stop() {
        recorder?.stop()
        recorder?.reset()
        recorder = null
    }
}