package br.com.projectbank.pojo

import com.fasterxml.jackson.annotation.JsonProperty

class TranferPojo (
    @JsonProperty("success")
    var success : Boolean,
    @JsonProperty("message")
    var message : String
)
