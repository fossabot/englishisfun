import com.android.build.gradle.internal.dsl.ProductFlavor
import org.gradle.api.NamedDomainObjectContainer

interface BuildProductFlavor {
    val name: String

    fun app(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor

    fun library(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor
}

object ProductFlavorDevelop : BuildProductFlavor {
    override val name = "dev"

    override fun app(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        container.create(name) {
            applicationIdSuffix = ".$name"
            versionNameSuffix = "-$name"
            dimension = BuildProductDimensions.ENVIRONMENT
        }

    override fun library(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        container.create(name) {
            versionNameSuffix = "-$name"
            dimension = BuildProductDimensions.ENVIRONMENT
        }
}

object ProductFlavorQA : BuildProductFlavor {
    override val name = "qa"

    override fun app(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        container.create(name) {
            applicationIdSuffix = ".$name"
            versionNameSuffix = "-$name"
            dimension = BuildProductDimensions.ENVIRONMENT
        }

    override fun library(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        container.create(name) {
            versionNameSuffix = "-$name"
            dimension = BuildProductDimensions.ENVIRONMENT
        }
}

object ProductFlavorProduction : BuildProductFlavor {
    override val name = "prod"

    override fun app(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        create(container)

    override fun library(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        create(container)

    private fun create(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        container.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
        }
}
