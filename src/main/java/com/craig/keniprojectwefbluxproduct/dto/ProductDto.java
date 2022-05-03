package com.craig.keniprojectwefbluxproduct.dto;

import com.craig.keniprojectwefbluxproduct.document.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto  {

    private String id;

    private String name;

    private String description;

    private String image;

    private String price;

    private Category category;


}
