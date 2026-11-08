package com.kinecut.app.data.api

import okhttp3.Dns
import java.net.Inet4Address
import java.net.InetAddress

class Ipv4FirstDns : Dns {
    override fun lookup(hostname: String): List<InetAddress> {
        val addresses = Dns.SYSTEM.lookup(hostname)
        return addresses.sortedBy { it !is Inet4Address }
    }
}
