package {{ cookiecutter.screen_package }}

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.insets.navigationBarsWithImePadding
import androidx.compose.foundation.layout.statusBarsPadding
import ru.kode.mileonair.core.ui.uikit.screen.MileonairController
import ru.kode.mileonair.core.ui.uikit.theme.AppTheme
import {{ cookiecutter.screen_package }}.{{ cookiecutter.screen_name }}Screen.ViewIntents
import {{ cookiecutter.screen_package }}.{{ cookiecutter.screen_name }}Screen.ViewState

internal class {{ cookiecutter.screen_name }}Controller :
  MileonairController<ViewState, ViewIntents>() {

  override fun createConfig(): Config<ViewIntents> {
    return object : Config<ViewIntents> {
      override val intentsConstructor = ::ViewIntents
    }
  }

  override fun handleBack(): Boolean {
    intents.navigateOnBack()
    return true
  }

  @Composable
  override fun ScreenContent(state: ViewState) {
    Column(
      modifier = Modifier
        .statusBarsPadding()
        .navigationBarsWithImePadding(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        text = state.example,
        style = AppTheme.typography.h1,
        color = AppTheme.colors.white,
        textAlign = TextAlign.Center,
      )
    }
  }
}
