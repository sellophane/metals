package tests.pc

import tests.BaseCompletionSuite
import tests.BuildInfoVersions

class CompletionExtensionSuite extends BaseCompletionSuite {

  override def requiresJdkSources: Boolean = true

  override def excludedScalaVersions: Set[String] =
    BuildInfoVersions.scala2Versions.toSet

  check(
    "extension",
    """
      |object Foo:
      |  extension (s: String)
      |    def double = s + s
      |    def double2 = s + s
      |  end extension
      |  "".dou@@ble
      |end Foo
    """.stripMargin,
    """|double=> String
       |double2=> String
    """.stripMargin
  )

  check(
    "extension_2",
    """
      |object Foo:
      |  extension (s: String)
      |    def double = s + s
      |    def double2 = s + s
      |  end extension
      |  dou@@ble
      |end Foo
    """.stripMargin,
    """|double(s: String): String
       |double2(s: String): String
    """.stripMargin
  )
}