### Contains
* This Repository contains performance test scripts, test data, utilities, summary reports of below MOSIP modules, 
    1. Pre-registration (UI and batch Jobs)
    2. Registration Processor
    3. ID Repository
    4. ID Authentication
    5. Kernel
	5. Resident services
* Open source Tools used,
    1. [Apache JMeter](https://jmeter.apache.org/)
    2. [Glowroot](https://glowroot.org/)

### How to run performance scripts using Apache JMeter tool
* Download Apache JMeter from https://jmeter.apache.org/download_jmeter.cgi
* Download scripts for the required module.
* Start JMeter by running the jmeter.bat file for Windows or jmeter file for Unix. 
* Validate the scripts for one user.
* Execute a dry run for 10 min.
* Execute performance run with various loads in order to achieve targeted NFR's.

### Apache JMeter scripts
* JMeter scripts for each module are available in the respective scripts folders.
	* [Pre-registration scripts](https://github.com/mosip/mosip-performance-tests-mt/tree/master/pre-registration/scripts).
	* [Registration Processor 	scripts](https://github.com/mosip/mosip-performance-tests-mt/tree/master/registration/registrationprocessor/scripts).
	* [ID Authentication scripts](https://github.com/mosip/mosip-performance-tests-mt/tree/master/id-authentication/scripts).
	* [ID Repository scripts](https://github.com/mosip/mosip-performance-tests-mt/tree/master/commons/id-repository/scripts).
	* [Kernel scripts](https://github.com/mosip/mosip-performance-tests-mt/tree/master/commons/kernel/scripts).
	* [Resident Services scripts](https://github.com/mosip/mosip-performance-tests-mt/tree/master/resident-services/scripts)
* [JAVA utilities](https://github.com/mosip/mosip-performance-tests-mt/tree/master/utilities).

### Execution steps for various scripts
* [Pre-registration execution steps](https://github.com/mosip/mosip-performance-tests-mt/blob/master/pre-registration/README.md)
* [Registration Processor execution steps](https://github.com/mosip/mosip-performance-tests-mt/blob/master/registration/registrationprocessor/README.md)
* [ID Authentication execution steps](https://github.com/mosip/mosip-performance-tests-mt/blob/master/id-authentication/README.md)
* [ID Repository execution steps](https://github.com/mosip/mosip-performance-tests-mt/blob/master/commons/id-repository/README.md)
* [Kernel execution steps](https://github.com/mosip/mosip-performance-tests-mt/blob/master/commons/kernel/README.md)
* [Resident Services execution steps](https://github.com/mosip/mosip-performance-tests-mt/blob/master/resident-services/README.md)
* [JAVA utilities execution steps](https://github.com/mosip/mosip-performance-tests-mt/blob/master/utilities/README.md)

---

### How to contribute
You can contribute to MOSIP! If you see a problem, or have inputs on how we can improve, please see the [Contributor Guide](https://github.com/mosip/mosip-docs/wiki/Contributor-Guide) on how to file bugs, contribute code, and more.

### License
This project is licensed under the terms of [Mozilla Public License 2.0](https://github.com/mosip/mosip-platform/blob/master/LICENSE)

### Communication
Join the [developer mailing list](https://groups.io/g/mosip-dev)

You may also be interested in joining our community room on Gitter via [![Gitter](https://badges.gitter.im/mosip-community/community.svg)](https://gitter.im/mosip-community/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)  where you could get some great community support
