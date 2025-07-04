package ru.cft.feature_user_details.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import ru.cft.feature_user_details.R
import ru.cft.feature_user_details.presentation.UserDetailsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
    modifier: Modifier,
    viewModel: UserDetailsViewModel,
    onBackClick: () -> Unit,
) {

    val user = viewModel.userUI

    UserDetailsScreenImpl(
        modifier = modifier,
        user = user,
        onBackClick = onBackClick
    )
}

@Composable
private fun UserDetailsScreenImpl(modifier: Modifier, user: UserUI, onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = stringResource(R.string.back),
            modifier = Modifier
                .size(32.dp)
                .clickable(onClick = onBackClick)
        )
        Text(
            text = stringResource(R.string.user_details),
            fontSize = 32.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
    Box(modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 64.dp)) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                UserDetailsCard(user = user)
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun UserDetailsCard(user: UserUI) {

    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = user.picture,
                contentDescription = stringResource(R.string.user_picture),
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )

            Text(
                text = user.fullName,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            HorizontalDivider()

            InfoRow(label = stringResource(R.string.gender), value = user.gender)
            InfoRow(
                label = stringResource(R.string.email),
                value = user.email,
                intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = "mailto:${user.email}".toUri()
                }
            )
            InfoRow(
                label = stringResource(R.string.phone),
                value = user.phone,
                intent = Intent(Intent.ACTION_DIAL).apply {
                    data = "tel:${user.phone}".toUri()
                }
            )
            InfoRow(
                label = stringResource(R.string.cell),
                value = user.cell,
                intent = Intent(Intent.ACTION_DIAL).apply {
                    data = "tel:${user.cell}".toUri()
                }
            )
            InfoRow(label = stringResource(R.string.username), value = user.username)
            InfoRow(label = stringResource(R.string.nationality), value = user.nationality)
            InfoRow(label = stringResource(R.string.date_of_birth), value = user.dob)
            InfoRow(label = stringResource(R.string.registered), value = user.registered)

            HorizontalDivider()

            Text(
                text = stringResource(R.string.address),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = user.address,
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    val uri = Uri.encode(user.address)
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = "geo:0,0?q=$uri".toUri()
                    }
                    context.startActivity(intent)
                }
            )

            HorizontalDivider()

            Text(
                stringResource(R.string.coordinates),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = user.coordinates,
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    val lat = user.coordinates.split(' ')[1]
                    val lon = user.coordinates.split(' ')[3]
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = "geo:$lat$lon?q=$lat$lon".toUri()
                    }
                    context.startActivity(intent)
                }
            )

            HorizontalDivider()

            Text(stringResource(R.string.timezone), fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(
                text = user.timezone,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String?, intent: Intent? = null) {
    if (!value.isNullOrEmpty()) {
        val context = LocalContext.current
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "$label:", fontWeight = FontWeight.Bold)

            if (intent != null) {
                Text(
                    text = value,
                    textDecoration = TextDecoration.Underline, // подчёркивание
                    modifier = Modifier.clickable { context.startActivity(intent) }
                )
            } else {
                Text(value)
            }
        }
    }
}