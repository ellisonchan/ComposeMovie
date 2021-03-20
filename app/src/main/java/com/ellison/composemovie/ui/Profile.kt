package com.ellison.composemovie.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer

import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.ScrollState

import androidx.compose.foundation.layout.heightIn

import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider


import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

import com.ellison.composemovie.R
import com.ellison.composemovie.bean.Account
import com.ellison.composemovie.bean.myAccount
import com.ellison.composemovie.ui.theme.shapes

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(myAccount)
}

@Composable
fun Profile(account: Account) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    ProfileHeader(
                        scrollState,
                        this@BoxWithConstraints.maxHeight,
                        account.Post
                    )
//                    Spacer(modifier = Modifier.height(2.dp))

                    NameAndPosition(
                        stringResource(id = account.FullName),
                        stringResource(id = account.About)
                    )

                    ProfileProperty(
                        stringResource(R.string.display_name),
                        stringResource(id = account.NickName)
                    )

//                    ProfileProperty(
//                        stringResource(R.string.status),
//                        stringResource(id = account.Status)
//                    )

                    ProfileProperty(
                        stringResource(R.string.sns),
                        stringResource(id = account.Sns),
                        isLink = true
                    )

                    EditProfile()
                }
            }
        }
    }
}

@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    containerHeight: Dp,
    post: Int
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        painter = painterResource(id = post),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun NameAndPosition(name: String, position: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Name(
            name,
            modifier = Modifier.paddingFromBaseline(32.dp)
        )
        Position(
            position,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .paddingFromBaseline(24.dp)
        )
    }
}

@Composable
private fun Name(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier,
        style = MaterialTheme.typography.h5
    )
}

@Composable
private fun Position(position: String, modifier: Modifier = Modifier) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = position,
            modifier = modifier,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun ProfileProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.paddingFromBaseline(24.dp),
                style = MaterialTheme.typography.caption
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(color = Color.Blue)
        } else {
            MaterialTheme.typography.body1
        }
        Text(
            text = value,
            modifier = Modifier.paddingFromBaseline(24.dp),
            style = style
        )
    }
}

@Composable
private fun EditProfile() {
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        onClick = {},
        shape = shapes.large
    ) {
        Text(
            text = stringResource(id = R.string.btn_edit),
        )
    }
}