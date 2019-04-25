package gov.nist.asbestos.simapiTest

import gov.nist.asbestos.simapi.toolkit.configDatatypes.server.SimulatorActorType
import gov.nist.asbestos.simapi.toolkit.toolkitApi.BasicSimParameters
import gov.nist.asbestos.simapi.toolkit.toolkitApi.EngineSpi
import gov.nist.asbestos.simapi.toolkit.toolkitApi.SimulatorBuilder
import gov.nist.asbestos.simapi.toolkit.toolkitServicesCommon.SimConfig
import spock.lang.Shared
import spock.lang.Specification

class SimBuilderSpec extends Specification {
    @Shared String toolkitPort = '8888'
    @Shared String toolkitUrl = "http://localhost:${toolkitPort}/xdstools2"
    @Shared EngineSpi engine

    def setupSpec() {
        SimulatorBuilder builder = new SimulatorBuilder(toolkitUrl)
        engine = builder.engine
    }

    def 'build reg sim' () {
        setup:
        BasicSimParameters regParams = new BasicSimParameters()
        regParams.id = 'reg'
        regParams.user = 'default'
        regParams.actorType = SimulatorActorType.REGISTRY
        regParams.environmentName = 'default'

        when:
        engine.deleteIfExists(regParams.id, regParams.user)

        then:
        !engine.exists(engine.getSimId(regParams))

        when:
        SimConfig regConfig = engine.create(regParams)

        then:
        regConfig
        regConfig.asString('Creation Time').size() > 5
    }
}
