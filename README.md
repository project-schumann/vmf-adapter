# vmf-adapter
Java adapter to read VMF and extract important details

## Setup

This adapter is tested under Java 7. Dependency and build management is handled using ``Gradle`` 2.2.

### Building vmf-adapter

From the project root, run:

    $ gradle build
    
This task will compile all classes, assemble the JAR and run the unit tests. If any test fails, the build will fail.

The resultant JAR is found at ``build/libs/vmf-adapter-#.#.#.jar``

### Testing vmf-adapter

From the project root, run:

    $ gradle test

A test result report can be found at ``build/reports/tests/index.html``
    
### Cleaning the Build

Gradle produces all artifacts in a directory called ``build`` within the project root. To remove this directory, run:

    $ gradle clean
    
### Generating Javadoc

Javadoc is provided on all packages, classes, and method. To generate the Javadoc, run:

    $ gradle javadoc
    
The resultant Javadoc is found at ``build/docs/javadoc/index.html``
    
### Generating a Test Coverage Report

Jacoco is used for determining test coverage. To produce a test coverage report, run:

    $ gradle jacocoTestReport
    
The resultant test coverage report is found in ``build/jacocoHtml/index.html``

## Usage

To use the adapter, an instance of ``VMFAdapter`` must be instantiated.

The ``VMFAdapter`` class has two constructors, one taking a ``File`` instance referencing
the VMF file and the other taking a ``VectorMusic`` instance obtained from the vmf-parser.

    VMFAdapter adapter = new VMFAdapter(new File(vmfURI));

With this ``VMFAdapter`` instance initialized, the VMF file can be adapted (abbreviated) as follows:

    AbbreviatedVectorMusic abbreviatedMusic = adapter.abbreviate();
    
The ``abbreviate()`` method of the ``VMFParser`` class will return an ``AbbreviatedVectorMusic`` instance which is the root class of
the object model representing an abbreviated VMF file. This abbreviated format has a simplified data set. An important
aspect of this abbreviated format is that notes are encoded in a relative form where the change in semitones from the last
note in a piece of music is recorded rather than an absolute reference to a pitch class and octave.
 
The API for this object model can be found in the [Javadoc](http://project-schumann.github.io/vmf-adapter/).