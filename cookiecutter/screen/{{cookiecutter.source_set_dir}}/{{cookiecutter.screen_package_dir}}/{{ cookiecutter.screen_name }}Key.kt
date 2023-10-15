package {{ cookiecutter.screen_package }}

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.kode.base.ui.core.util.ToothpickScreenBindings
import ru.kode.base.ui.mvi.core.model.ComponentConfig
import ru.kode.base.ui.mvi.core.model.ScreenKey

@Parcelize
object {{ cookiecutter.screen_name }}Key : ScreenKey() {
  // See NOTE_IGNORED_ON_PARCEL_AND_OBJECT
  @Suppress("INAPPLICABLE_IGNORED_ON_PARCEL")
  @IgnoredOnParcel
  override val componentConfig = ComponentConfig(
    presenterClass = {{ cookiecutter.screen_name }}Presenter::class.java,
    controllerClass = {{ cookiecutter.screen_name }}Controller::class.java,
    screenBindings = ToothpickScreenBindings {
      bind({{ cookiecutter.screen_name }}Wiring::class.java)
        .singletonInScope()
    }
  )
}
