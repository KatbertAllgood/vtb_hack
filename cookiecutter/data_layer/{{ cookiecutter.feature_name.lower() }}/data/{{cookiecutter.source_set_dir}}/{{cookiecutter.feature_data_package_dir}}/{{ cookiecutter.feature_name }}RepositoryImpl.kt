package {{ cookiecutter.feature_data_package }}

import {{ cookiecutter.feature_data_package }}.network.{{ cookiecutter.feature_name }}Api
import {{ cookiecutter.feature_domain_package }}.{{ cookiecutter.feature_name }}Repository
import ru.kode.pathfinder.PathFinder
import javax.inject.Inject

internal class {{ cookiecutter.feature_name }}RepositoryImpl @Inject constructor(
  private val {{ cookiecutter.feature_name.lower() }}Api: {{ cookiecutter.feature_name }}Api,
  private val pathFinder: PathFinder,
) : {{ cookiecutter.feature_name }}Repository
