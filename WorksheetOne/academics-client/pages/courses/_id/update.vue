<template>
    <div>
        <b-container>
            <h1 class="mb-4 mt-4">
                Update Course #{{ course['code'] }}
            </h1>
            <form @submit.prevent="create">
                <b-row class="mb-3">
                    <b-col><strong>Name:</strong></b-col>
                    <b-col><input v-model="course['name']" type="text"/></b-col>
                </b-row>
                <b-row class="mb-3">
                    <b-col>
                        <nuxt-link to="/">
                            <button class="btn btn-warning">Return</button>
                        </nuxt-link>
                    </b-col>
                    <b-col>
                        <button type="reset" class="btn btn-primary">RESET</button>
                        <button @click.prevent="update" class="btn btn-light">UPDATE</button>
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
            course: {
                'name': null,
                'code': null
            }
        }
    },
    created () {
        this.$axios.$get('api/courses/' + this.$route.params.id).then((course) => {
            this.course = course
        })
    },
    methods: {
        update: function () {
            this.$axios.$put('api/courses/' + this.$route.params.id, {
                "name": this.course.name
            })
        }
    }
}
</script>
