import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedAddButton(
    modifier: Modifier = Modifier,
    // You can pass a callback here if you want to tell the parent the new number
    onQuantityChange: (Int) -> Unit = {}
) {
    // Internal state to track if we are open or closed
    var isExpanded by remember { mutableStateOf(false) }
    var quantity by remember { mutableStateOf(0) }

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
            modifier = Modifier.padding(4.dp) // Inner padding
        ) {

            // --- THE HIDDEN PART (Minus & Number) ---
            // Only visible when expanded
            AnimatedVisibility(visible = isExpanded) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // MINUS BUTTON
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Decrease",
                        modifier = Modifier
                            .size(32.dp) // Touch target size
                            .clip(CircleShape)
                            .clickable {
                                if (quantity > 1) {
                                    quantity--
                                    onQuantityChange(quantity)
                                } else {
                                    // If we go below 1, collapse back to just "+"
                                    quantity = 0
                                    onQuantityChange(0)
                                    isExpanded = false
                                }
                            }
                            .padding(6.dp) // Icon visual size padding
                    )

                    // THE NUMBER
                    Text(
                        text = "$quantity",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }

            // --- THE VISIBLE PART (Plus Button) ---
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable {
                        if (!isExpanded) {
                            // First click: Expand and set to 1
                            isExpanded = true
                            quantity = 1
                            onQuantityChange(1)
                        } else {
                            // Subsequent clicks: Just add
                            quantity++
                            onQuantityChange(quantity)
                        }
                    }
                    .padding(6.dp)
            )
        }
    }
}