package com.opc.bestpodcast.ui.podcasts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opc.bestpodcast.R

@Composable
fun PodcastSearchField(
    searchValue: String,
    onSearchValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchValue,
        onValueChange = onSearchValueChanged,
        modifier =
        modifier
            .fillMaxWidth()
            .padding(4.dp),
        label = {
            Text(stringResource(R.string.rechercher_un_podcast))
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    )
}

@Preview
@Composable
fun PreviewPodcastSearchField() {
    val searchValue = remember { mutableStateOf("") }
    PodcastSearchField(
        searchValue = searchValue.value,
        onSearchValueChanged = {
            searchValue.value = it
        }
    )
}
