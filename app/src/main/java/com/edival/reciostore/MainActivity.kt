package com.edival.reciostore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.edival.reciostore.ui.theme.RecioStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecioStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) { LoginContent() }
            }
        }
    }
}

@Composable
fun LoginContent() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (iconApp, textApp, card) = createRefs()
        val topIcon = createGuidelineFromTop(0.2f)
        val topCard = createGuidelineFromTop(0.6f)
        Icon(
            modifier = Modifier
                .height(dimensionResource(R.dimen.icon_big_size))
                .constrainAs(iconApp) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topIcon)
                    bottom.linkTo(textApp.top)
                },
            painter = painterResource(R.drawable.outline_store),
            contentDescription = stringResource(R.string.app_icon)
        )
        Text(modifier = Modifier.constrainAs(textApp) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(iconApp.bottom)
        }, text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.h3)

    }
}