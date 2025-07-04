package ru.cft.feature_users_list.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import ru.cft.common.ui.EnterAnimation
import ru.cft.feature_users_list.R
import ru.cft.feature_users_list.presentation.UsersListState
import ru.cft.feature_users_list.presentation.UsersListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersListScreen(
    modifier: Modifier,
    viewModel: UsersListViewModel = koinViewModel(),
    onItemClick: (String) -> Unit,
) {

    val state = viewModel.state.collectAsState()
    var isRefreshing = state.value is UsersListState.Loading
    val pullRefreshState = rememberPullToRefreshState()

    UsersListScreenImpl(
        modifier = modifier,
        userListState = state.value,
        isRefreshing = isRefreshing,
        pullRefreshState = pullRefreshState,
        onRefresh = {
            isRefreshing = true
            viewModel.loadUsersList()
            isRefreshing = false
        },
        onItemClick = { index: Int ->
            val encodedUserModel = viewModel.getEncodedUserModel(index)
            onItemClick(encodedUserModel)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersListScreenImpl(
    modifier: Modifier,
    userListState: UsersListState,
    isRefreshing: Boolean,
    pullRefreshState: PullToRefreshState,
    onRefresh: () -> Unit,
    onItemClick: (Int) -> Unit,
) {


    PullToRefreshBox(
        isRefreshing = isRefreshing,
        state = pullRefreshState,
        modifier = modifier,
        onRefresh = onRefresh
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = stringResource(R.string.user_list),
                fontSize = 32.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        when (userListState) {
            is UsersListState.Loading -> {}

            is UsersListState.Content -> {
                EnterAnimation {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 64.dp, start = 16.dp, end = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        itemsIndexed(userListState.users) { index, user ->
                            UserCard(
                                user,
                                onClick = { onItemClick(index) })
                        }
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }

            is UsersListState.Failure -> {
                ErrorDialog(
                    message = userListState.message,
                    onRefresh = onRefresh
                )
            }
        }
    }
}

@Composable
private fun UserCard(user: UserUI, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(90.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = CircleShape
                    ),
                model = user.picture,
                contentDescription = stringResource(R.string.user_picture),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = user.fullName,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )
                HorizontalDivider()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = stringResource(R.string.phone_icon),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = user.phone, fontSize = 16.sp
                    )
                }
                HorizontalDivider()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = stringResource(R.string.address_icon),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = user.address, fontSize = 16.sp
                    )
                }
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun ErrorDialog(message: String, onRefresh: () -> Unit) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = stringResource(R.string.error_title),
                    fontSize = 16.sp
                )
            },
            text = {
                Text(
                    text = message,
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        onRefresh()
                    }
                ) {
                    Text(text = stringResource(R.string.retry))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text(text = stringResource(R.string.dismiss))
                }
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun UsersListScreen_Preview() {
    UsersListScreenImpl(
        modifier = Modifier.fillMaxSize(),
        userListState = UsersListState.Content(
            listOf(
                UserUI(
                    fullName = "mr Wade Barnett",
                    picture = "-",
                    phone = "8 800 555 35 35",
                    address = "USA, Michigan, Mill Road, 223"
                )
            )
        ),
        isRefreshing = false,
        pullRefreshState = rememberPullToRefreshState(),
        onRefresh = {},
        onItemClick = {}
    )
}