package com.example.myapp.composable.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapp.R
import com.example.myapp.Retrofit.LiveData
import com.example.myapp.data.MyViewModel

@Composable
fun LiveItem(liveData: LiveData, myViewModel: MyViewModel, navController: NavController) {
    parsingDate(liveData.startDate)
    parsingPrice(liveData.priceInfo)
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = liveData.posterUrl,
            contentDescription = liveData.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(175.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    myViewModel.selectedLiveData = liveData
                    navController.navigate(route = "GroupDetailScreen2")
                },
            error = painterResource(id = R.drawable.defaultimg)
        )

        Column {
            Text(
                text = liveData.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = "날짜: " +parsingDate(liveData.startDate),
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(start = 15.dp)
            )
            Text(
                text = "가격: " + parsingPrice(liveData.priceInfo),
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}

fun parsingDate(input: String): String {
    return input.substring(0, 10)
}
fun parsingPrice(input: String): String {
    val regex = Regex("""([\d,.]+)(?=\s*원|\s*won)""")
    val match = regex.find(input)
    return match?.groupValues?.get(1)?.replace(".", ",")?.let {
        if (it.contains(Regex("""\d"""))) "${it}원" else it
    } ?: "가격정보 없음"
}