# LinguAdde
Java CLI for adding translations from a CSV to JSON and XLIFF files.

LinguAdde is currently at version `1.0`.

## Requirements

The only requirement is `Java SE Runtime Environment 8` or higher.

## Install

The latest release of this project can be found at [releases](https://github.com/Theyssen/LinguAdde/releases)
or can be run from source.

## Usage

Use `java -jar LinguAdde-1.0.jar help` to see the usage information.

```bash
usage: java -jar LinguAdde-1.0.jar -c <file> [-d <delimiter>] -t <folder>
Add translations in data files

 -c,--csv-file <file>         CSV file with translationdata
 -d,--delimiter <delimiter>   CSV delimiter Default: ;
 -t,--target <folder>         Target translation files
```

| Option | Long option | Argument | Required | Description |
| --- | --- | --- | --- | --- |
| c | csv-file | file | yes | CSV file with translationdata _**Important:**_ This file has to be encoded in `UTF-8` |
| d | delimiter | char | no | CSV delimiter **_Default:_** `;` |
| t | target | folder | yes | Target translation files _**Important:**_ The files in this folder have to be encoded in `UTF-8` |

## Example

```bash
java -jar LinguAdde-1.0.jar -c translations.csv -t results
```

## Author

Christophe Theyssen

[Github](https://github.com/Theyssen)
[Bitbucket](https://bitbucket.org/Theyssen/)
[Linkedin](https://be.linkedin.com/in/christophe-theyssen-39779487)
