package com.example.shopflow.debug

import com.example.shopflow.R
import com.example.shopflow.model.dataclass.Product

/**
 * A sample list of products for debugging and testing purposes.
 * Each product includes details like name, description, price, image, category, rating, etc.
 */
val sampleProducts = listOf(
    Product(
        1,
        "Clarity Cleanser",
        "Gentle foam cleanser for all skin types. Removes impurities without drying.",
        444.00,
        355.20,
        R.drawable.product_image,
        "Cleansers",
        5f,
        249,
        true,
        true,
        false,
        "Best Seller"
    ), Product(
        2,
        "Radiance Serum",
        "Vitamin C serum for a brighter, even complexion. Fades dark spots.",
        644.00,
        400.00,
        R.drawable.product_image,
        "Serums",
        4f,
        123,
        true,
        false,
        true,
        "New"
    ), Product(
        3,
        "HydroBoost Gel",
        "Lightweight gel moisturizer for oily to combination skin. Hydrates instantly.",
        744.00,
        499.00,
        R.drawable.product_image,
        "Moisturizers",
        3.5f,
        45,
        false,
        false,
        true
    ), Product(
        4,
        "SunGuard SPF 50",
        "Broad-spectrum sunscreen, non-greasy formula. Protects against UVA/UVB.",
        344.00,
        200.00,
        R.drawable.product_image,
        "Sunscreens",
        4.1f,
        450,
        true,
        true,
        false
    ), Product(
        5,
        "Detox Clay Mask",
        "Purifying clay mask with activated charcoal. Minimizes pores.",
        244.00,
        199.00,
        R.drawable.product_image,
        "Masks",
        5f,
        899,
        true,
        true,
        true
    ), Product(
        6,
        "Night Cream",
        "Rich night cream with retinol and peptides. Reduces fine lines.",
        944.00,
        699.00,
        R.drawable.product_image,
        "Moisturizers",
        4.5f,
        654,
        false,
        false,
        false
    )
)