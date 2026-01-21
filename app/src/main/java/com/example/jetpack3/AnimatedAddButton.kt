import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedAddButton(
    quantity: Int,
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonSize = 26.dp
    val contentPadding = buttonSize * 0.1f
    val fontSize = (buttonSize.value * 0.45).sp

    val isExpanded = quantity > 0

    Surface(
        modifier = modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ),
        shape = CircleShape,
        color = Color.Black,
        contentColor = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(contentPadding)
        ) {

            AnimatedVisibility(visible = isExpanded) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Decrease",
                        modifier = Modifier
                            .size(buttonSize)
                            .clip(CircleShape)
                            .clickable { onRemoveClick() }
                            .padding(contentPadding),
                        tint = Color.White
                    )

                    Text(
                        text = "$quantity",
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                }
            }


            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase",
                modifier = Modifier
                    .size(buttonSize)
                    .clip(CircleShape)
                    .clickable { onAddClick() }
                    .padding(contentPadding),
                tint = Color.White
            )
        }
    }
}