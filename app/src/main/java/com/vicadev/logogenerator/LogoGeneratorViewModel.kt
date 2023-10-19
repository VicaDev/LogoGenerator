package com.vicadev.logogenerator

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.audio.TranscriptionRequest
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.file.FileSource
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.image.ImageCreation
import com.aallam.openai.api.image.ImageSize
import com.aallam.openai.api.logging.LogLevel
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.LoggingConfig
import com.aallam.openai.client.OpenAI
import com.vicadev.logogenerator.audio.AudioRecorder
import com.vicadev.logogenerator.conf.Conf
import com.vicadev.logogenerator.conf.Env
import kotlinx.coroutines.launch
import okio.source
import java.io.File
import kotlin.time.Duration.Companion.seconds

class LogoGeneratorViewModel : ViewModel() {

    private var openAI = OpenAI(
        token = Env.OPENAI_API_KEY,
        logging = LoggingConfig(LogLevel.All),
        timeout = Timeout(socket = 60.seconds),
    )

    var info: String by mutableStateOf("")

    var loading: Boolean by mutableStateOf(false)
    var recording: Boolean by mutableStateOf(false)

    private var recorder: AudioRecorder? = null
    private var audioFile: File? = null


    //Audio
    fun recordAudio(context: Context) {
        if (recording) {
            recording = false
            recorder?.stop()
            loadInfo(audioFile)
        } else {
            if (recorder == null) {
                recorder = AudioRecorder(context)
            }
            File(context.cacheDir, Conf.AUDIO_FILE).also {
                recorder?.record(it)
                audioFile = it
                recording = true
            }
        }
    }

    //Resumen
    fun createInfoSummary() = viewModelScope.launch {

        startLoading()

        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId(Conf.GPT_MODEL),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = "Eres un asistente especializado en resumir textos de manera extremadamente concisa y eficaz"
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = "Lista las palabras claves del siguiente texto: $info"
                )
            )
        )

        info =
            openAI.chatCompletion(chatCompletionRequest).choices.first().message?.content.toString()

        endLoading()
    }

    //Generación Logo
    fun generateLogo(games: String, elements: String, imageURL: (String) -> Unit) =
        viewModelScope.launch {

            startLoading()

            var prompt = "eSports logo, vector logo, ${games.trim()}, ${elements.trim()}, "

            if (info.isNotEmpty()) {
                prompt += " ${info.trim()}"
            }

            val images = openAI.imageURL(
                creation = ImageCreation(
                    prompt,
                    n = 1,
                    size = ImageSize.is1024x1024
                )
            )

            imageURL(images.first().url)

            endLoading()
        }

    //Transcripción
    private fun loadInfo(file: File?) = viewModelScope.launch {

        file?.source()?.let {

            startLoading()

            val transcriptionRequest = TranscriptionRequest(
                audio = FileSource(name = Conf.AUDIO_FILE, source = it),
                model = ModelId(Conf.WHISPER_MODEL)
            )

            val transcription = openAI.transcription(transcriptionRequest)

            info = transcription.text

            endLoading()
        }
    }

    //Loading
    private fun startLoading() {
        loading = true
    }

    private fun endLoading() {
        loading = false
    }
}
















