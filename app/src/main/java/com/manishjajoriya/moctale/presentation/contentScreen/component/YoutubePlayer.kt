package com.manishjajoriya.moctale.presentation.contentScreen.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.manishjajoriya.moctale.domain.model.content.Video
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubePlayer(video: Video, modifier: Modifier = Modifier) {
  val ctx = LocalContext.current
  AndroidView(
      factory = {
        var view = YouTubePlayerView(it)
        val fragment =
            view.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                  override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadVideo(video.videoId, 1f)
                  }
                }
            )
        view
      }
  )
}
