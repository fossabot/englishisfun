import com.android.build.gradle.internal.dsl.ProductFlavor
import org.gradle.api.NamedDomainObjectContainer

interface BuildProductFlavor {
    val name: String

    fun app(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor

    fun library(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor
}

/**
 * Base product flavor class that allows to alter the applicationId and the versionName so as
 * being able to create different versions of the same codebase.
 *
 * @param name ApplicationId and versionName suffix
 */
open class ProductFlavorBase(override val name: String) : BuildProductFlavor {

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

/**
 * Develop product flavor implementation using "dev" as suffix.
 */
object ProductFlavorDevelop : ProductFlavorBase("dev")

/**
 * QA product flavor implementation using "qa" as suffix.
 */
object ProductFlavorQA : ProductFlavorBase("qa")

/**
 * Production product flavor implementation.
 */
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
