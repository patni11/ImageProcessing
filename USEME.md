Supported Script Commands:

- load - load file_name image_name
  - load download.jpeg dog 
  - image must be one of the popular image formats(jpg, ppm, png, etc.)
  - file must also be existing
- save - save file_name image_name
  - save sepiaDog.jpeg sepiaDog
  - image_name must reference an existing image in the storage/software
- blur - blur image_name image_name
  - blur dog blurryDog
  - the first image_name must reference a valid image loaded into the storage/software.
- sharpen - sharpen image_name image_name
  - sharpen dog sharpDog
  - the first image_name must reference a valid image loaded into the storage/software.
- greyscale - greyscale image_name image_name
  - greyscale dog greyDog
  - the first image_name must reference a valid image loaded into the storage/software.
- red-component - red-component image_name image_name
  - red-component dog redGreyScaledDog
  - the first image_name must reference a valid image loaded into the storage/software.
- blue-component - blue-component image_name image_name
  - blue-component dog blueGreyScaledDog
  - the first image_name must reference a valid image loaded into the storage/software.
- green-component - green-component image_name image_name
  - green-component dog greenGreyScaledDog
  - the first image_name must reference a valid image loaded into the storage/software.
- brighten - brighten value image_name image_name
  - brighten 50 dog brightDog
  - value must be an integer
  - the first image_name must reference a valid image loaded into the storage/software.
- sepia - sepia image_name image_name
  - sepia dog sepiaDog
  - the first image_name must reference a valid image loaded into the storage/software.
- horizontal-flip - horizontal-flip image_name image_name
  - horizontal-flip dog horizontalDog
  - the first image_name must reference a valid image loaded into the storage/software.
- vertical-flip - vertical-flip image_name image_name
  - vertical-flip dog verticalDog
  - the first image_name must reference a valid image loaded into the storage/software.
- file - file file_name
  - file script.txt
  - file_name must reference an existing locatable file.
- luma-component - luma-component image_name image_name
  - luma-component dog lumaGreyScaledDog
  - the first image_name must reference a valid image loaded into the storage/software.
- value-component - value-component image_name image_name
  - value-component dog valueGreyScaledDog
  - the first image_name must reference a valid image loaded into the storage/software.
- intensity-component - intensity-component image_name image_name
  - intensity-component dog intensityGreyScaledDog
  - the first image_name must reference a valid image loaded into the storage/software.