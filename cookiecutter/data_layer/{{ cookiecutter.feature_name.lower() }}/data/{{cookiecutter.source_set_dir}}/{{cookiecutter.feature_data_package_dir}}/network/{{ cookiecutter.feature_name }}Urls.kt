package {{ cookiecutter.feature_data_package }}.network

import ru.kode.mileonair.core.data.network.UrlSpecifications
import ru.kode.pathfinder.UrlSpec

enum class {{ cookiecutter.feature_name }}Urls(val spec: UrlSpec) {
  ExampleRequest(
    UrlSpec(
      pathTemplate = "example/request",
      httpMethod = UrlSpec.HttpMethod.POST,
      name = "Execute example request"
    )
  );

  val id = spec.id

  companion object {
    val urlSpecs = UrlSpecifications(
      items = values().map { it.spec }
    )
  }
}
