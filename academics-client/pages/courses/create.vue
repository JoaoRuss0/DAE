<template>
    <div>
        <b-container>
            <h1 class="mb-4 mt-4">
                New Course
            </h1>
            <form @submit.prevent="create" :disabled="!isFormValid">
                <b-row class="mb-3">
                    <b-col><strong>Name:</strong></b-col>
                    <b-col><b-input v-model.trim="name" type="text" :state="isNameValid" placeholder="Enter course's name" required/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col><strong>Code:</strong></b-col>
                    <b-col><b-input v-model="code" :state="isCodeValid" placeholder="Enter course's code" required/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col>
                        <nuxt-link :to="{name: 'courses'}">
                            <button class="btn btn-warning">Return</button>
                        </nuxt-link>
                    </b-col>
                    <b-col>
                        <button type="reset" class="btn btn-primary">RESET</button>
                        <button @click.prevent="create" :disabled="!isFormValid" class="btn btn-success">CREATE</button>
                    </b-col>
                </b-row>
            </form>
        </b-container>
    </div>
</template>

<script>
export default {
    data () {
        return {
            name: null,
            code: null
        }
    },
    computed: {
        isNameValid () {
            if(!this.name){
                return null
            }
            let nameLength = this.name.length

            if(nameLength < 3 || nameLength > 30)
            {
                return false
            }

            return true;
        },
        isCodeValid () {
            if(!this.code){
                return null
            }
            let codeLength = this.code.length
            if(codeLength < 2 || codeLength > 5)
            {
                return false
            }
            return true
        },
        isFormValid () {
            if(!this.isNameValid){
                return false
            }
            if(!this.isCodeValid){
                return false
            }
            return true
        }
    },
    methods: {
        create () {
            this.$axios
                .$post('/api/courses', {
                    name: this.name,
                    code: this.code
                })
                .then(() => {
                    this.$router.push('/courses')
                })
        }
    }
}
</script>
