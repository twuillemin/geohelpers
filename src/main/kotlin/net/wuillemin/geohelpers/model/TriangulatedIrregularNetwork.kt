package net.wuillemin.geohelpers.model

/**
 * Class for Triangulated Irregular Networks
 */
class TriangulatedIrregularNetwork(patches: List<Triangle>) : PolyhedralSurface(patches, "TIN")