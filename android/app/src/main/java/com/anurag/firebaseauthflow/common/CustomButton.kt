package com.anurag.firebaseauthflow.common

import FirebaseAuthFlowTheme
import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(label: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.widthIn(0.dp, 200.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.outline)
    }
}

@Composable
fun CustomButtonV2(label: String, icon:ImageVector?, isLoading:Boolean=false, onClick: () -> Unit, buttonColors: ButtonColors = ButtonDefaults.buttonColors()){
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = buttonColors,
        shape = RoundedCornerShape(4.dp),
        enabled = !isLoading
    ) {
        if(isLoading){
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(20.dp)
            )
        }else{
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = null)
                Spacer(modifier = Modifier.width(width = 8.dp))
            }
        }
        Text(text = label)
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Preview
fun ButtonPreview() {
    val icon: ImageVector = Icons.TwoTone.ExitToApp
    val btnColors = ButtonDefaults.buttonColors(
        contentColor = MaterialTheme.colorScheme.onError,
        containerColor = MaterialTheme.colorScheme.error
    )
    FirebaseAuthFlowTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            CustomButtonV2(label = "Logout", icon = icon, onClick = { /*TODO*/ }, buttonColors = btnColors)
        }
    }
}