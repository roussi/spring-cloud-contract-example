import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description(
            '''
     Given : 
        Consumer want to be greeted 
     When : 
        Consumers make a call to /v2/hello
     Then:
        return World
    '''
    )
    request {
        method 'GET'
        url '/v2/hello'
    }
    response {
        headers {
            contentType(applicationJson())
        }
        status(OK())
        body("TchouPi")
    }
}