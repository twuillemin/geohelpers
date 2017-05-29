package net.wuillemin.geohelpers.model

/**
 * Class for all TriangulatedIrregularNetworks
 */
class TriangulatedIrregularNetwork(patches: List<Triangle>) : PolyhedralSurface(patches, "TIN")