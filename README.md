
# Bill Generator

![Bill Generator](https://your-image-url.png/)

## Overview

The Bill Generator is a powerful Java-based tool that allows you to effortlessly generate bills using a base PDF file as a template and a JSON data file to define the text placement. Whether you're running a small business, managing finances for a project, or just need to create customized bills, this tool has got you covered!

## Features

-   **Easy to Use:** With a simple command-line interface, you can quickly generate bills without any hassle.
    
-   **Customizable Templates:** The tool utilizes a base PDF file as a template, giving you complete freedom to design your bill's layout. Just create your perfect template once, and you're ready to generate countless customized bills.
    
-   **JSON Data Integration:** Simply provide a JSON file with the required text details and their respective positions on the bill. The tool will seamlessly merge the data with the template, saving you valuable time.
    
-   **Font and Styling Options:** Customize the appearance of your bills with various font styles, colors, and font sizes to make them look professional and appealing.
    

## Getting Started

To generate your bills, follow these simple steps:

1.  **Clone the Repository:** Start by cloning this repository to your local machine.
    
2.  **Compile the Code:** Use your favorite Java compiler to compile the code.
    
3.  **Prepare Your Template:** Ensure you have a base PDF file that will act as the template for your bills. Make sure it contains the necessary placeholders for the text elements you want to insert dynamically.
    
4.  **Create JSON Data:** Craft a JSON file containing the text details, positions, font styles, and colors for each element you want to place on the bill. Refer to the provided example JSON file for guidance.
    
5.  **Run the Application:** Use the command-line interface to specify the required options, including the template name, JSON input file, and the output PDF file path. For example:
    
 ```shell
 java -jar billGenerator.jar -t ./data/base-pdf/bill-template.pdf -j ./data/json/bill-data.json -o ./output/bill.pdf
 ``` 
    
6.  **Voila!** Your customized bill will be generated and saved at the specified output location.
    

## Example JSON Data

The JSON file should contain an array of objects, each representing a text element to be placed on the bill. Here's an example:

```json

[
  {
    "text": "Your Company Name",
    "locationX": 50,
    "locationY": 100,
    "fontSize": 14,
    "color": "#336699",
    "font": "Roboto-Bold.ttf"
  },
  {
    "text": "Invoice Number: 123456",
    "locationX": 60,
    "locationY": 200,
    "fontSize": 11,
    "color": "#444444",
    "font": "Helvetica-Regular.ttf"
  },
  {
    "text": "Total Amount: $100.00",
    "locationX": 80,
    "locationY": 250,
    "fontSize": 12,
    "color": "#222222",
    "font": "Arial-Bold.ttf"
  },
  {
    "enricher": "RandomNumber",
    "locationX": 125,
    "locationY": 300,
    "fontSize": 11,
    "color": "#222222",
    "font": "VictorMono-Bold.ttf"
  }
]
```

In this example, the JSON data instructs the tool to place various texts at specific positions on the bill and apply different font styles and colors.

## License

This project is licensed under the [MIT License](https://chat.openai.com/LICENSE).

## Contribution

We welcome contributions! If you have any ideas for improvements, bug fixes, or new features, feel free to open an issue or submit a pull request.

## Support

If you encounter any issues or have questions about using the Bill Generator, please [open an issue](https://github.com/amithgc/BillGenerator/issues).

## Acknowledgments

Special thanks to all contributors and the open-source community for making this project possible.

----------

Get ready to revolutionize the way you create bills! The Bill Generator empowers you to save time and effort while producing professional, customized bills for your needs. Give it a try and let us know your feedback. Happy billing! 🧾💼