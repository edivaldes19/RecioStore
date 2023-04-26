package com.edival.reciostore.presentation.screens.client.category.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.presentation.navigation.screen.client.ClientCategoryScreen

@Composable
fun ClientCategoryListItem(navHostController: NavHostController, category: Category) {
    Card(
        modifier = Modifier
            .padding(all = dimensionResource(R.dimen.padding_default))
            .clickable {
                navHostController.navigate(ClientCategoryScreen.ProductList.passCategory(category.toJson()))
            }, shape = RoundedCornerShape(
            topStart = dimensionResource(R.dimen.padding_default),
            topEnd = dimensionResource(R.dimen.padding_default)
        )
    ) {
        Column {
            category.img?.let { url ->
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.icon_big_size))
                        .padding(bottom = dimensionResource(R.dimen.padding_min)),
                    model = url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.padding_min),
                    vertical = dimensionResource(R.dimen.padding_ultra_min)
                ),
                text = category.name ?: stringResource(R.string.unknown_name),
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_min)),
                text = category.description ?: stringResource(R.string.unknown_description),
                maxLines = 1
            )
        }
    }
}