package gov.nist.asbestos.simapiTest

import gov.nist.asbestos.simapi.toolkit.toolkitApi.SimulatorBuilder
import spock.lang.Shared
import spock.lang.Specification

class SimBuilderSpec extends Specification {
    @Shared String toolkitPort = '8888'
    @Shared String toolkitUrl = "http://localhost:${toolkitPort}/xdstools2"
    @Shared SimulatorBuilder builder

    def setupSpec() {
        builder = new SimulatorBuilder(toolkitUrl)
    }

    def 'a test' () {
        when:
        def i = 1

        then:
        builder
    }
}
