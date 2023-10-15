package {{ cookiecutter.feature_domain_package }}.di

import {{ cookiecutter.feature_domain_package }}.{{ cookiecutter.feature_name }}Model
import {{ cookiecutter.feature_domain_package }}.{{ cookiecutter.feature_name }}ModelImpl
import toothpick.config.Module

class {{ cookiecutter.feature_name }}DomainModule : Module() {
  init {
    bind({{ cookiecutter.feature_name }}Model::class.java)
      .to({{ cookiecutter.feature_name }}ModelImpl::class.java)
      .singletonInScope()
  }
}
