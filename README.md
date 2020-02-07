# FamilyTree API

Amphora Core Services Engineer tests


## Installation

Package jar (it also will run all tests) then execute it:

```bash
mvn clean package
cd target 
java -jar amphora-1.0-SNAPSHOT.jar
```

## Usage

[http://localhost:8080/](http://localhost:8080) will redirect you to swagger-api.html page, where you can find methods GET and POST to work with app.

You can use both formats: json/xml

##### POST
Example of input data:
```
<?xml version="1.0" encoding="UTF-8"?>
<FamilyNodeRequest name=“Anna”>
	<age>1901</age>
	<father></father>
	<mother></mother>
</FamilyNodeRequest>

<?xml version="1.0" encoding="UTF-8"?>
<FamilyNodeRequest name=“Bob”>
	<age>1890</age>
	<father></father>
	<mother></mother>
</FamilyNodeRequest>
```

##### GET
* Nore: I use here one more param - generation - just to simplify visibility of result
(can not be used) (see notes section)
```
curl -X GET "http://localhost:8080/?ascending=true" -H "accept: application/json"
```
Output:
```
{
  "familyNodes": [
    {
      "name": "Bob",
      "age": 1890,
      "parents": [
        null,
        null
      ],
      "kids": [],
      "generation": 0
    },
    {
      "name": "Anna",
      "age": 1901,
      "parents": [
        null,
        null
      ],
      "kids": [],
      "generation": 0
    }
  ]
}
```

## Tasks

* Q1 - FamilyTree
```
 public FamilyNode save(String name, int age,
                           String father, String mother)
```
Test class - FamilyTreeTest

Chosen data structure - implemented as a tree, 
complexity of adding node: O(n), 
space for adding node: O(n)

* Q2, Q3 - SortFamilyTreeUtils

```
public static List<FamilyNode> sort(FamilyTree familyTree,
                                        Comparator<Integer> comparator)
```
Test class - SortFamilyTreeTest

* Q4, Q5 - PrintUtils
```
public static void printTree(FamilyTree familyTree)

public static void printUpwardsTreeFrom(FamilyNode kid) 
```

* Q6 - MissingMemberHandler, there is explanation of approach
```
    public static LinkedFamilyNode addMissingMember(LinkedFamilyNode sortedNodeHead,
                                                    FamilyNode missingNode) 
```
MissingMemberHandlerTest - carefully cover different test cases


* Q7 - FamilyTreeServiceImplTest - implemented integration test after implemented SptingBoot API

## Additional scope:

* Implemented Spring Boot RESTful API

* Added swagger UI documentation

## Notes
* I use here one more param - generation - just to simplify visibility of result
(can not be used)

* Constructions to the family node:
1. Names are unique (to simplify).
If we don't want our names to be unique,
we can just add id, that will be for example hash of {name + age}, and then everytime then we create a node - we provide id as the result

2. To simplify interface (as there is no constructions in conditions) - we can add only kids

3. If there is no parent - it's the most ancient parent

* JPA

I didn’t implement any entities using JPA (as it didn't asked),
but it also possible to create repository like this:

```
import com.kamalova.amphora.dao.model.FamilyNodeDao;
import org.springframework.data.repository.CrudRepository;

public interface FamilyTreeRepository
        extends CrudRepository<FamilyNodeDao, Long> {
}
```

* Testing

There could be more detailed tests

I didn't implement too many test like tests to check all edge cases (for example, to provide more details about HTTP API, it's better to add more validation on request data and more code errors with explanation