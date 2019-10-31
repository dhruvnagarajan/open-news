package com.dhruvnagarajan.opennews.router

/**
 * @author Dhruvaraj Nagarajan
 */
interface RootRouter {

    fun navigateRootFeature(featureId: Int): Boolean
}