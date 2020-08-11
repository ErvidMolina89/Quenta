package com.example.quenta.Connection.Resources

import com.example.pruebavalid.DataAccess.Connection.Handler.IServiceParameters

enum class Services (url : String,
                     method : IServiceParameters.Methods)
    : IServiceParameters {

    list_total_gallery("",IServiceParameters.Methods.GET),
    list_search_gallery("image_type=photo&q=",IServiceParameters.Methods.GET),
    ;

    private val url : String
    private val method : IServiceParameters.Methods
    private var complement: String = ""

    init {
        this.url = url
        this.method = method
    }

    override fun getURL(): String {
        return url + complement
    }

    override fun getMethods(): IServiceParameters.Methods {
        return method
    }

    override fun getUrlWithComplement(complement: String): Services {
        this.complement = complement
        return this
    }

}