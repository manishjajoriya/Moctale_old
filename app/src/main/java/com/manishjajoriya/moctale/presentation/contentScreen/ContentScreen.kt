package com.manishjajoriya.moctale.presentation.contentScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil3.compose.AsyncImage
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.R
import com.manishjajoriya.moctale.presentation.contentScreen.component.CategoryChip
import com.manishjajoriya.moctale.presentation.contentScreen.component.ContentInfo
import com.manishjajoriya.moctale.presentation.contentScreen.component.ContentSection
import com.manishjajoriya.moctale.presentation.contentScreen.component.CustomButton
import com.manishjajoriya.moctale.presentation.contentScreen.component.MoctaleMeter
import com.manishjajoriya.moctale.presentation.contentScreen.component.ProfileCircle
import com.manishjajoriya.moctale.presentation.contentScreen.component.Review
import com.manishjajoriya.moctale.presentation.contentScreen.component.TicketBook
import com.manishjajoriya.moctale.presentation.contentScreen.component.VibeChart
import com.manishjajoriya.moctale.ui.theme.Gray
import com.manishjajoriya.moctale.ui.theme.Inter
import com.manishjajoriya.moctale.ui.theme.Pink
import kotlin.math.roundToInt

@Composable
fun ContentScreen(
    slug: String?,
    contentViewModel: ContentViewModel,
    modifier: Modifier = Modifier,
) {

  LaunchedEffect(slug) {
    contentViewModel.loadContent(slug)
    contentViewModel.loadReview(slug)
  }
  val content = contentViewModel.content
  val reviews = contentViewModel.reviews
  val scrollState = rememberScrollState()
  // Perfect to Negative
  val reviewColors =
      listOf(Color(0xFFB048FF), Color(0xFF00D391), Color(0xFFFCB700), Color(0xFFFE647E))

  if (contentViewModel.loading) {
    Box(contentAlignment = Alignment.Center) { CircularProgressIndicator() }
  } else {
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
    ) {
      content?.let {
        // Top banner image
        AsyncImage(
            model = content.banner,
            modifier = Modifier.height(280.dp).fillMaxWidth(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
        Column(Modifier.padding(Constants.smallPadding)) {
          ContentInfo(content = content)
          CustomButton(title = "Mark as Watched", icon = R.drawable.ic_open_eye_icon, color = Pink)
          Spacer(Modifier.height(Constants.extraSmallPadding))
          CustomButton(
              title = "Add to Collection",
              icon = R.drawable.ic_bookmark_icon,
              color = Gray,
          )
          ContentSection(title = "Overview", showHorizontalDivider = false) {
            Text(
                text = content.description,
                Modifier.padding(top = Constants.mediumPadding),
                style = TextStyle(fontFamily = Inter, color = Gray),
            )
          }

          FlowRow(
              modifier = Modifier.fillMaxWidth().padding(8.dp),
              horizontalArrangement = Arrangement.Start,
              verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            content.categoryList.forEach {
              CategoryChip(genre = it.name, modifier = Modifier.padding(end = 8.dp))
            }
          }
          HorizontalDivider(Modifier.padding(top = Constants.smallPadding))

          // Vibe Chart
          if (content.genreList.isNotEmpty()) {
            ContentSection(title = "Vibe Chart") {
              Column {
                VibeChart(
                    genres = content.genreList,
                    modifier = Modifier.fillMaxWidth().height(300.dp),
                )
                FlowRow(
                    modifier = Modifier.padding(Constants.mediumPadding).fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Constants.mediumPadding),
                    verticalArrangement = Arrangement.spacedBy(Constants.mediumPadding),
                ) {
                  content.genreList.forEach { it ->
                    Row(
                        Modifier.padding(end = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                      Box(
                          Modifier.size(12.dp)
                              .clip(CircleShape)
                              .background(Color(it.color.toColorInt()))
                      )
                      Spacer(Modifier.padding(horizontal = Constants.extraSmallPadding))
                      Text(
                          text = "${it.name} (${it.percentage}%)",
                          style = TextStyle(fontFamily = Inter, fontWeight = FontWeight.Medium),
                      )
                    }
                  }
                }
              }
            }
          }

          // Cast List
          if (content.actorList.isNotEmpty()) {
            ContentSection(title = "Cast") {
              LazyRow(
                  Modifier.fillMaxWidth()
                      .heightIn(min = 200.dp)
                      .padding(top = Constants.mediumPadding),
                  horizontalArrangement =
                      Arrangement.spacedBy(Constants.extraSmallPadding, Alignment.Start),
              ) {
                val actorList = content.actorList
                items(
                    actorList.size,
                    key = { actorList[it].name + actorList[it].slug },
                ) { it ->
                  val actor = actorList[it]
                  ProfileCircle(image = actor.image, name = actor.name, character = actor.character)
                }
              }
            }
          }

          // Crew List
          if (content.crewList.isNotEmpty()) {
            ContentSection(title = "Crew") {
              LazyRow(
                  Modifier.fillMaxWidth()
                      .heightIn(min = 200.dp)
                      .padding(top = Constants.mediumPadding),
                  horizontalArrangement =
                      Arrangement.spacedBy(Constants.extraSmallPadding, Alignment.Start),
              ) {
                val crewList = content.crewList
                items(
                    crewList.size,
                    key = { crewList[it].name + crewList[it].slug },
                ) { it ->
                  val crew = crewList[it]
                  ProfileCircle(
                      image = crew.image,
                      name = crew.name,
                      character = crew.roleList.reduce { final, str -> "$final, $str" },
                  )
                }
              }
            }
          }

          // Ticket On
          if (content.hasTickets) {
            ContentSection(title = "Ticket On") {
              LazyRow(
                  Modifier.fillMaxWidth().padding(Constants.mediumPadding),
                  horizontalArrangement =
                      Arrangement.spacedBy(Constants.largePadding, Alignment.Start),
              ) {
                val ticketSiteList = content.ticketingSiteList
                items(ticketSiteList.size) { TicketBook(ticketSiteList[it]) }
              }
            }
          }

          // Moctale Meter
          ContentSection(title = "Moctale Meter") {
            val typeOfReview = listOf("Perfection", "Go fot it", "Timepass", "Skip")
            val reviewCount =
                listOf(
                    content.countNegativeReview,
                    content.countNeutralReview,
                    content.countPositiveReview,
                    content.countPerfectReview,
                )
            val reviewPercentage =
                listOf(
                    content.percentNegativeReview,
                    content.percentNeutralReview,
                    content.percentPositiveReview,
                    content.percentPerfectReview,
                )
            val totalReviewCount = content.countTotalReview

            MoctaleMeter(Modifier, reviewCount, reviewPercentage, totalReviewCount)
            FlowRow(
                modifier =
                    Modifier.fillMaxWidth()
                        .padding(vertical = Constants.mediumPadding)
                        .offset(y = (-110).dp),
                horizontalArrangement = Arrangement.Center,
                maxItemsInEachRow = 1,
            ) {
              reviewPercentage.reversed().forEachIndexed { index, value ->
                Row(
                    modifier =
                        Modifier.fillMaxWidth(0.6f).padding(vertical = Constants.extraSmallPadding),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                  Row(
                      verticalAlignment = Alignment.CenterVertically,
                      horizontalArrangement = Arrangement.spacedBy(8.dp),
                  ) {
                    Box(
                        modifier =
                            Modifier.size(12.dp).clip(CircleShape).background(reviewColors[index])
                    )
                    Text(text = typeOfReview[index], style = TextStyle(fontFamily = Inter))
                  }
                  Text(
                      text = "${reviewPercentage[reviewPercentage.size - index - 1].roundToInt()}%",
                      style = TextStyle(fontFamily = Inter),
                  )
                }
              }
            }
          }

          // Reviews
          ContentSection(title = "Reviews") {
            reviews?.let {
              reviews.data.take(10).forEach { Review(data = it, reviewColors = reviewColors) }
            }
          }
        }
      }
    }
  }
}
