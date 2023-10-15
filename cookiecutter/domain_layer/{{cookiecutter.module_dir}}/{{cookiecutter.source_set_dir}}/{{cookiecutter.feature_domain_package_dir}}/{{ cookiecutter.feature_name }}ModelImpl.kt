package {{ cookiecutter.feature_domain_package }}

import kotlinx.coroutines.flow.MutableStateFlow
import ru.kode.mileonair.core.domain.model.ReactiveModel
import javax.inject.Inject

internal class {{ cookiecutter.feature_name }}ModelImpl @Inject constructor(
  private val {{ cookiecutter.feature_name.lower() }}Repository: {{ cookiecutter.feature_name }}Repository,
) : ReactiveModel(), {{ cookiecutter.feature_name }}Model {

  object State

  private val state = MutableStateFlow(State)
}
