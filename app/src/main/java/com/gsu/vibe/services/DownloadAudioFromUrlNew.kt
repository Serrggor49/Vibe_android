package com.gsu.vibe.services

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import java.io.BufferedInputStream
import java.net.URL

object DownloadAudioFromUrlNew {

     //fun download(vararg p0: String?): String {
      fun download(fileName: String, urlString: String, context: Context) {


        val url  = URL(urlString)
        val connection = url.openConnection()
        connection.connect()
        val inputStream = BufferedInputStream(url.openStream())
        //val filename = "audio.mp3"
        val outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        val data = ByteArray(1024)

        var count = inputStream.read(data)
        var total = count
        while (count != -1) {
            outputStream.write(data, 0, count)
            count = inputStream.read(data)
            total += count
        }
        outputStream.flush()
        outputStream.close()
        inputStream.close()

        Log.d("MyLogs991", "finished saving audio.mp3 to internal storage")
       // println("finished saving audio.mp3 to internal storage")
     //   return "Success"
    }


    // /data/data/com.gsu.vibe/files/audio.mp3
}